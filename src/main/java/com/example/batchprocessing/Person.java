package com.example.batchprocessing;

import java.io.Serializable;

public record Person(String firstName, String lastName) implements Serializable {

}
