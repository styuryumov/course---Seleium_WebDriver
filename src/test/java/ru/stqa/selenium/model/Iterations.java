package ru.stqa.selenium.model;

public class Iterations {

    public static Builder newEntity() {
        return new Iterations().new Builder();
    }

    private int iterations;
    private String size;

    public int getIterations() {
        return iterations;
    }

    public String getSize() {
        return size;
    }

    public class Builder {
        private Builder() {
        }

        public Builder withIterations(int iterations) {
            Iterations.this.iterations = iterations;
            return this;
        }

        public Builder withSize(String size) {
            Iterations.this.size = size;
            return this;
        }

        public Iterations build() {
            return Iterations.this;
        }
    }
}
