package com.slabadniak.task5.sessioncontent;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DataContext {
     Collection get();
     void insert(ResultSet resultSet);
}
