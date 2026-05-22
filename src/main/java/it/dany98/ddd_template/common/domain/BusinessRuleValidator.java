package it.dany98.ddd_template.common.domain;

import it.dany98.ddd_template.common.AssertionConcern;

public abstract class BusinessRuleValidator extends AssertionConcern {
    protected void checkRule(BusinessRule rule) throws BusinessRuleValidationException {
        if (!rule.isRuleComplied()) {
            throw new BusinessRuleValidationException(rule);
        }
    }
}
