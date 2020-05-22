package com.example.demo.message;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author wll
 * @desc Message01
 * @link
 * @date 2020/5/21 8:41 下午
 */
@Data
@Accessors(chain = true)
public class Message01  {
    public static final String TOPIC = "DEMO_01";

    /**
     * 编号
     */
    private Integer id;


}
