package com.analytics.v1.domain.sql;

import com.analytics.v1.domain.dim.DimInfo;
import com.analytics.v1.domain.dim.DimMeta;
import com.analytics.v1.domain.point.PointInfo;
import com.analytics.v1.domain.point.PointMeta;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author wutangsheng
 * @create 2022-05-10 1:49 下午
 * @desc
 */
@Slf4j
public class SqlMetaBuilder {
    private final LinkedList<DimMeta> dimMetas;
    private final ArrayDeque<PointMeta> pointMetas;

    private final LinkedList<SqlMeta> sqlList;

    private final List<DimInfo> allDims;

    /**
     * 临时变量
     */
    private PointMeta pointMeta;

    public SqlMetaBuilder(LinkedList<DimMeta> dimMetas, ArrayDeque<PointMeta> pointMetas) {
        this.dimMetas = dimMetas;
        this.pointMetas = pointMetas;
        this.sqlList = new LinkedList<>();
        this.allDims = this.allDims();
    }

    private boolean hasPoint() {
        return !this.pointMetas.isEmpty();
    }

    /**
     * 验证是否合法，以及队列是否还有数据
     *
     * @return boolean
     */
    private boolean validation() {
        if (this.pointMeta == null) {
            return false;
        }
        return this.pointMeta.check();
    }

    public LinkedList<SqlMeta> builder() {
        while (this.hasPoint()) {
            this.pointMeta = pointMetas.poll();
            if (this.validation()) {
                String sql = this.select() + this.from() + this.where();
                sqlList.add(new SqlMeta(sql, allDims, this.pointMeta.getPointInfos()));
            }
        }
        return this.sqlList;
    }

    private List<DimInfo> allDims() {
        ArrayList<DimInfo> dimInfos = new ArrayList<>();
        for (DimMeta dimMeta : this.dimMetas) {
            dimInfos.addAll(dimMeta.getDimInfos());
        }
        return dimInfos;
    }

    /**
     * 行列转换
     *
     * @return
     */
    private String select() {
        String s = this.pointSelect();

        String pointSelect = s.substring(0, s.length() - 2);
        return "select " + "\n" +
                //把所有维度查出来
                this.dimSelect() +
                pointSelect + "\n";
    }


    private String from() {
        StringBuilder builder = new StringBuilder();
        if (dimMetas.isEmpty()) {
            builder.append("from ").append(this.pointMeta.tableName()).append(" AS ").append(this.pointMeta.tableName());
            return builder.toString();
        }

        //指标表 from
        builder.append("from ").append(this.pointMeta.tableName()).append(" AS ").append(this.pointMeta.tableName()).append("\n");
        //关联维度表
        String join = join();

        builder.append(join);

        return builder.toString();
    }


    private String where() {
        return "";
    }


    String groupBy() {
        if (this.dimMetas.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(" group by ").append("\n");
        int flag = dimMetas.size() - 1;
        for (DimMeta dimMeta : this.dimMetas) {
            for (DimInfo dimInfo : dimMeta.getDimInfos()) {
                if (flag != 0) {
                    builder.append(dimMeta.tableName()).append(".").append(dimInfo.getDimName()).append(",").append("\n");
                    flag--;
                    continue;
                }
                builder.append(dimMeta.tableName()).append(".").append(dimInfo.getDimName()).append(";");
            }
        }
        return builder.toString();
    }

    private String dimSelect() {
        StringBuilder stringBuilder = new StringBuilder("");
        for (DimMeta dimMeta : this.dimMetas) {
            for (DimInfo dimInfo : dimMeta.getDimInfos()) {
                stringBuilder.append(dimMeta.tableName()).append(".").append(dimInfo.getDimFieldName()).append(",").append("\n");
            }
        }
        return stringBuilder.toString();
    }

    private String pointSelect() {
        StringBuilder stringBuilder = new StringBuilder();
        for (PointInfo pointInfo : this.pointMeta.getPointInfos()) {
            switch (pointInfo.getPointRule()) {
                case DISTINCT_COUNT:
                    stringBuilder.append(distinctCount(pointInfo)).append("\n");
                    continue;
                case SUM:
                    stringBuilder.append(sum(pointInfo)).append("\n");
                    continue;
                default:
                    stringBuilder.append(count(pointInfo)).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    private String sum(PointInfo pointInfo) {
        return String.format("sum(%s.%s) as %s ,", this.pointMeta.tableName(), pointInfo.getPointFieldName(), pointInfo.getPointFieldName());
    }

    private String count(PointInfo pointInfo) {
        return String.format("count(%s.%s) as %s ,", this.pointMeta.tableName(), pointInfo.getPointFieldName(), pointInfo.getPointFieldName());
    }

    private String distinctCount(PointInfo pointInfo) {
        return String.format("count( distinct %s.%s) as %s ,", this.pointMeta.tableName(), pointInfo.getPointFieldName(), pointInfo.getPointFieldName());
    }

    private String join() {
        StringBuilder builder = new StringBuilder();
        for (DimMeta dimMeta : dimMetas) {
            builder.append(" LEFT JOIN ").append(dimMeta.tableName()).append(" AS ").append(dimMeta.tableName()).append("\n");
        }
        String on = on();
        builder.append(on);
        return builder.toString();
    }

    /**
     * 统计粒度有部分不一样的情况下，要相同的那个
     *
     * @return
     */
    private String on() {
        List<String> list1 = Arrays.asList(this.pointMeta.countParttio());
        List<String> list2 = new ArrayList<>();
        for (DimMeta dimMeta : this.dimMetas) {
            Collections.addAll(list2, dimMeta.countParttio());
        }

        //求交集
        list2.retainAll(list1);
        //去重
        HashSet<String> set = new HashSet<>(list2);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("on").append(" ");
        int flag = 0;
        for (DimMeta dimMeta : this.dimMetas) {
            for (String granularity : set) {
                if (flag == 0) {
                    stringBuilder.append(this.pointMeta.tableName())
                            .append(".")
                            .append(granularity)
                            .append("=")
                            .append(" ")
                            .append(dimMeta.tableName())
                            .append(".")
                            .append(granularity)
                            .append(" ")
                            .append("\n");
                    flag++;
                    continue;
                }
                stringBuilder.append("and ")
                        .append(this.pointMeta.tableName())
                        .append(".")
                        .append(granularity)
                        .append("=")
                        .append(" ")
                        .append(dimMeta.tableName())
                        .append(".")
                        .append(granularity)
                        .append(" ")
                        .append("\n");

            }
        }
        return stringBuilder.toString();
    }

    private String filter() {
        return "";
    }


}
