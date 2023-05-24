package com.fintechloans.model.services;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GsonLocalDateAdapter extends TypeAdapter<LocalDate> {
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @Override
  public void write(JsonWriter out, LocalDate value) throws IOException {
    if (value == null) {
      out.nullValue();
    } else {
      out.value(value.format(FORMATTER));
    }
  }

  @Override
  public LocalDate read(JsonReader in) throws IOException {
    if (in.hasNext()) {
      String dateStr = in.nextString();
      return LocalDate.parse(dateStr, FORMATTER);
    }
    return null;
  }
}
