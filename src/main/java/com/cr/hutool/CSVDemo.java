package com.cr.hutool;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.*;
import cn.hutool.core.util.CharsetUtil;
import com.cr.common.Facility;

import java.io.File;
import java.util.List;

import static com.cr.common.Facility.print;

public class CSVDemo {

    public static void main(String[] args) {
        CsvReader reader = CsvUtil.getReader();
        CsvData data = reader.read(new File("Dana+AFI_data"));
        List<CsvRow> rows = data.getRows();
        rows.forEach(row -> {
            print(row.getRawList());
            print(row.get(0));
        });

        CsvWriter writer = CsvUtil.getWriter("/Users/dlinka/IdeaProjects/java/Fill_Dana+AFI_data", CharsetUtil.CHARSET_UTF_8);
        writer.write(new String[]{"1", "2", "3"}, new String[]{"4", "5", "6"}, new String[]{"7", "8", "9"});
    }
}
