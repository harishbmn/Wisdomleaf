package com.wisdomleaftest.screens.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wisdomleaftest.screens.main.model.Datum;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface IListView {

    void setList(Object model) throws JSONException, IOException;

}
