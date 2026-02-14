package me.marin.calcoverlay.io;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import me.marin.calcoverlay.util.OverlayUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class AllAdvancementsSettings {

    @Expose
    @SerializedName("columns")
    public List<ColumnData> columnData;

    @Expose
    @SerializedName("rows")
    public List<RowData> rowData;

    public static AllAdvancementsSettings loadDefaultSettings() {
        AllAdvancementsSettings instance = new AllAdvancementsSettings();

        instance.columnData = new ArrayList<>();
        instance.columnData.add(new ColumnData(ColumnType.ICONS, HeaderRow.NOTHING, true));
        instance.columnData.add(new ColumnData(ColumnType.LOCATION, HeaderRow.TEXT, true));
        instance.columnData.add(new ColumnData(ColumnType.NETHER_COORDS, HeaderRow.TEXT, true));
        instance.columnData.add(new ColumnData(ColumnType.ANGLE, HeaderRow.TEXT, true));

        instance.rowData = new ArrayList<>();
        instance.rowData.add(new RowData(RowType.STRONGHOLD, true));
        instance.rowData.add(new RowData(RowType.SPAWN, true));
        instance.rowData.add(new RowData(RowType.OUTPOST, true));
        instance.rowData.add(new RowData(RowType.MONUMENT, true));

        return instance;
    }


    @Data @AllArgsConstructor
    public static class ColumnData {
        @Expose @SerializedName("name")
        private final ColumnType columnType;
        @Expose @SerializedName("header row")
        private HeaderRow headerRow;

        @Expose @SerializedName("visible")
        private boolean isVisible;
    }

    @AllArgsConstructor
    @Getter
    public enum HeaderRow {
        @Expose @SerializedName("nothing")
        NOTHING("空白"),
        @Expose @SerializedName("show text")
        TEXT("文本");

        private final String display;

        public static HeaderRow match(String s) {
            for (HeaderRow value : HeaderRow.values()) {
                if (value.display.equals(s)) {
                    return value;
                }
            }
            return null;
        }
    }

    @AllArgsConstructor
    @Getter
    public enum ColumnType {
        @Expose @SerializedName("icons")
        ICONS("图标", ""),
        @Expose @SerializedName("location")
        LOCATION("位置", "位置"),
        @Expose @SerializedName("nether coords")
        NETHER_COORDS("下界", "下界"),
        @Expose @SerializedName("angle")
        ANGLE("角度", "角度");

        private final String configDisplay;
        private final String overlayDisplay;
    }

    @Data @AllArgsConstructor
    public static class RowData {
        @Expose @SerializedName("name")
        private final RowType rowType;

        @Expose @SerializedName("visible")
        private boolean isVisible;
    }

    @AllArgsConstructor
    @Getter
    public enum RowType {
        STRONGHOLD("要塞", OverlayUtil.strongholdIconImage),
        SPAWN("潜影贝", OverlayUtil.spawnIconImage),
        OUTPOST("掠夺者前哨站", OverlayUtil.outpostIconImage),
        MONUMENT("海底神殿", OverlayUtil.monumentIconImage);

        private final String configDisplay;
        private final Image icon;
    }


}
