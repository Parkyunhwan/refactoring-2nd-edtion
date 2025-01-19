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

    public List<Performance> getPerformances() {
        return performances;
    }

    public String getCustomer() {
        return customer;
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

        public int getAudience() {
            return audience;
        }
    }
}
