package com.jy.coinhub.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.io.IOException;
import java.util.List;

@Getter
public class UpbitWithdrawlFee {

    private boolean success;
    private String data;

    private static ObjectMapper mapper = new ObjectMapper();

    public List<UpbitEachWithdrawlFee> getData() throws IOException {
        return mapper.readValue(data, new TypeReference<>() {});
    }
}
