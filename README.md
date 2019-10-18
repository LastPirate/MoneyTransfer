# MoneyTransfer API
Simple RESTful API for money transfers between accounts.

# Actual change notes
* Decided to cut solution for problem like multi currency, currency conversion and showing transfer history
* Added unit tests and refactoring of existing ones.
* Included a little changes for avoiding deadblocks.

### Main features
* Based on personal user accounts;
* Multicurrency for each user;
* Currency conversion at the domestic rate (for transfers between wallets in different currencies);
* Refill and withdrawal funds;
* Available user and general transfer book;
* All accounts and wallets could cloaseable.

### Other features
* Contains 12 endpoints for easy manage your money:
* Include full test coverage;
* JSON responce format;
* Notifications for typical exceptions;
* Use in-memory datastore.

### Start
You can find actual executable standalone file in `/target` repository dir.
Use standart `java -jar *filename*.jar` command to start server.

### Compile
This is Maven project so use `mvn clean compile` in repository dir.
