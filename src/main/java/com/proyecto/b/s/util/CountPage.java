package com.proyecto.b.s.util;

import java.util.List;

public class CountPage {
    public static Long countPages(Long listSize){

        return (listSize + 9) / 10;
    }
    public static Long countPagesList(List<Long> listSize){

        return (long) ((listSize.size() + 9) / 10);

    }

}
