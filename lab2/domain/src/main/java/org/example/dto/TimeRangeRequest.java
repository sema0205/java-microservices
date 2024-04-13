package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class TimeRangeRequest {

    private Timestamp start;
    private Timestamp end;

}

