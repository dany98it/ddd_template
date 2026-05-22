package it.dany98.ddd_template.common.domain;

public interface BusinessRule {
    boolean isRuleComplied();
    String message();
}
