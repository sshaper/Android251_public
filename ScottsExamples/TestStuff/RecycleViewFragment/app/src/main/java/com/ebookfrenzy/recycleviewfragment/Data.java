package com.ebookfrenzy.recycleviewfragment;

import java.util.ArrayList;
import java.util.List;

public class Data {
    List<String> list = new ArrayList<>();

    public Data(){
        genList();
    }

    private void genList(){
        list.add("China");
        list.add("Mexico");
        list.add("France");
        list.add("Germany");
        list.add("India");
        list.add("Russia");
        list.add("United Kingdom");
        list.add("United States");
    }

    public List<String> getList(){
        return list;
    }


}
