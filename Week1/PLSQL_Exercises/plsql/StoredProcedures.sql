-- =================================================================
-- Exercise 3: Stored Procedures
-- =================================================================

-- -----------------------------------------------------------------
-- Scenario 1: Process Monthly Interest (1%) for Savings Accounts
-- -----------------------------------------------------------------
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
BEGIN
    UPDATE Accounts
    SET Balance = Balance + (Balance * 0.01)
    WHERE UPPER(AccountType) = 'SAVINGS';

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Monthly interest of 1% applied to all Savings Accounts.');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error processing monthly interest: ' || SQLERRM);
END ProcessMonthlyInterest;
/


-- -----------------------------------------------------------------
-- Scenario 2: Update Employee Bonus by Department
-- -----------------------------------------------------------------
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_Department IN VARCHAR2,
    p_BonusPercentage IN NUMBER
) IS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * (p_BonusPercentage / 100))
    WHERE UPPER(Department) = UPPER(p_Department);

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Bonus of ' || p_BonusPercentage || '% applied to department: ' || p_Department);
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error updating employee bonus: ' || SQLERRM);
END UpdateEmployeeBonus;
/


-- -----------------------------------------------------------------
-- Scenario 3: Transfer Funds Between Accounts
-- -----------------------------------------------------------------
CREATE OR REPLACE PROCEDURE TransferFunds (
    p_SourceAccountID IN NUMBER,
    p_TargetAccountID IN NUMBER,
    p_Amount IN NUMBER
) IS
    v_SourceBalance NUMBER;
BEGIN
    -- Validate transfer amount
    IF p_Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Transfer amount must be greater than zero.');
    END IF;

    -- Check source account balance with lock for update
    SELECT Balance INTO v_SourceBalance
    FROM Accounts
    WHERE AccountID = p_SourceAccountID
    FOR UPDATE;

    -- Check sufficient funds
    IF v_SourceBalance < p_Amount THEN
        RAISE_APPLICATION_ERROR(-20002, 'Insufficient funds in source account.');
    END IF;

    -- Deduct from source account
    UPDATE Accounts
    SET Balance = Balance - p_Amount
    WHERE AccountID = p_SourceAccountID;

    -- Add to target account
    UPDATE Accounts
    SET Balance = Balance + p_Amount
    WHERE AccountID = p_TargetAccountID;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Successfully transferred $' || p_Amount || 
                         ' from Account ' || p_SourceAccountID || 
                         ' to Account ' || p_TargetAccountID);

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Invalid Account ID provided.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transfer failed: ' || SQLERRM);
END TransferFunds;
/