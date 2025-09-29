package model;

public enum Priority {
    HIGH, MEDIUM, LOW;

    public static Priority fromString(String s) {
        if (s == null) return MEDIUM;
        switch (s.trim().toUpperCase()) {
            case "HIGH": return HIGH;
            case "LOW":  return LOW;
            default:     return MEDIUM;
        }
    }
}
