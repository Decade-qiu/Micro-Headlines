package org.example.pojo;

import lombok.Data;

@Data
public class Portal {
    private String keyWords;
    private Integer type;
    private Integer pageNum = 1;
    private Integer pageSize =10;
}
