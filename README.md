# Openapi - LocalDateTime Parsing issue

Generated converter is unable to parse LocalDateTime because of error:
```
java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING 
```

See https://github.com/swagger-api/swagger-codegen/issues/6992

# Proguard - VerifyError on logical operations

Reproduce a bug described [over here](https://stackoverflow.com/questions/46853209/verifyerror-on-logical-operations-using-proguard)
Originally reproduced in https://github.com/aveuiller/proguardBugReproduction
