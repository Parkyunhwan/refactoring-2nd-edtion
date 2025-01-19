package com.refactoring.chapter1;

import java.util.List;

/*
[
  {
    "customer": "BigCo",
    "performances": [
      {
        "playID": "hamlet",
        "audience": 55
      },
      {
        "playID": "as-like",
        "audience": 35
      },
      {
        "playID": "othello",
        "audience": 40
      }
    ]
  }
]
 */
public class Invoice {
    private String customer;
    private List<Performance> performances;

    public Invoice(String customer, List<Performance> performances) {
        this.customer = customer;
        this.performances = performances;
    }

    public static class Performance {
        private String playID;
        private int audience;

        public Performance(String playID, int audience) {
            this.playID = playID;
            this.audience = audience;
        }

        public String getPlayID() {
            return playID;
        }

        public void setPlayID(String playID) {
            this.playID = playID;
        }
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
