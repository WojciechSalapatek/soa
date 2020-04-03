package com.wpate.four.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@AllArgsConstructor(staticName = "of")
public class Seat implements Serializable {

    private long id;
    private int row;
    private int column;
    private int price;
    private boolean isAvailable;

}
