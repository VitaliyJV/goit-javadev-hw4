package DTO;

import lombok.Data;

@Data
public class Worker {
    private long id_worker;
    private String name;
    private String birthday;
    private String level;
    private int salary;
}
