-- =================================================================
-- Exercise 1: Control Structures
-- =================================================================

SET SERVEROUTPUT ON;

-- -----------------------------------------------------------------
-- Scenario 1: Apply 1% interest rate discount for customers aged > 60
-- -----------------------------------------------------------------
DECLARE
    CURSOR c_senior_customers IS
        SELECT c.CustomerID, l.LoanID, l.InterestRate
        FROM Customers c
        JOIN Loans l ON c.CustomerID = l.CustomerID
        WHERE TRUNC(MONTHS_BETWEEN(SYSDATE, c.DOB) / 12) > 60;
BEGIN
    FOR rec IN c_senior_customers LOOP
        UPDATE Loans
        SET InterestRate = InterestRate - 1
        WHERE LoanID = rec.LoanID;

        DBMS_OUTPUT.PUT_LINE('Applied 1% discount to Loan ID: ' || rec.LoanID || 
                             ' for Customer ID: ' || rec.CustomerID);
    END LOOP;
    COMMIT;
END;
/

-- -----------------------------------------------------------------
-- Scenario 2: Promote customers to VIP status if Balance > $10,000
-- -----------------------------------------------------------------
DECLARE
    CURSOR c_vip_candidates IS
        SELECT CustomerID, Balance
        FROM Customers
        WHERE Balance > 10000;
BEGIN
    FOR rec IN c_vip_candidates LOOP
        UPDATE Customers
        SET IsVIP = 'TRUE'
        WHERE CustomerID = rec.CustomerID;

        DBMS_OUTPUT.PUT_LINE('Customer ID: ' || rec.CustomerID || 
                             ' promoted to VIP (Balance: $' || rec.Balance || ')');
    END LOOP;
    COMMIT;
END;
/

-- -----------------------------------------------------------------
-- Scenario 3: Print reminders for loans due within the next 30 days
-- -----------------------------------------------------------------
DECLARE
    CURSOR c_due_loans IS
        SELECT c.CustomerID, c.Name, l.LoanID, l.DueDate
        FROM Customers c
        JOIN Loans l ON c.CustomerID = l.CustomerID
        WHERE l.DueDate BETWEEN SYSDATE AND (SYSDATE + 30);
BEGIN
    FOR rec IN c_due_loans LOOP
        DBMS_OUTPUT.PUT_LINE('REMINDER: Customer ' || rec.Name || 
                             ' (ID: ' || rec.CustomerID || 
                             ') - Loan ID ' || rec.LoanID || 
                             ' is due on ' || TO_CHAR(rec.DueDate, 'YYYY-MM-DD'));
    END LOOP;
END;
/6