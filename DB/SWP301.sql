-- Create the Role table

CREATE TABLE Roles (
    RoleID int identity(1,1) PRIMARY KEY,
    RoleName VARCHAR(255)
);

-- Create the Account table
CREATE TABLE Account (
    AccountID int identity(1,1) PRIMARY KEY,
    UserName VARCHAR(255),
    Password VARCHAR(255),
    Email VARCHAR(255),
    Status INT
);

-- Create the Rank table
CREATE TABLE Ranks (
    RankID int identity(1,1) PRIMARY KEY,
    RankName VARCHAR(255),
    DayToRentRankAt VARCHAR(255),
    Price DECIMAL(10, 2)
);

-- Create the User table
CREATE TABLE Users (  -- Enclosed in brackets since 'User' is a reserved keyword
    UserID int identity(1,1) PRIMARY KEY,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    PhoneNumber VARCHAR(20),
    Address VARCHAR(255),
    Biography TEXT,
    Coins DECIMAL(10, 2),
    CreatedAt VARCHAR(255),
    RankID INT,
    RoleID INT,
    DateOfBirth DATE,
    LastLogin DATE,
    AccountID INT,
    ProfilePicture TEXT, -- Assuming longtext is equivalent to TEXT for SQL Server
    BackgroundPicture TEXT,
    FollowCounts INT,
    Follower INT,

    FOREIGN KEY (RankID) REFERENCES Ranks(RankID),
    FOREIGN KEY (RoleID) REFERENCES Roles(RoleID),
    FOREIGN KEY (AccountID) REFERENCES Account(AccountID)
);


-- Create the Following table
CREATE TABLE Following (
    FollowID int identity(1,1) PRIMARY KEY,
    FollowerID INT,
    FollowingID INT,
    DateFollow DATE,
    FOREIGN KEY (FollowerID) REFERENCES Users(UserID),
    FOREIGN KEY (FollowingID) REFERENCES Users(UserID)
);

-- Create the Payment table
CREATE TABLE Payment (
    PaymentID int identity(1,1) PRIMARY KEY,
    Amount DECIMAL(10, 2),
    CreatedAt DATE,
    UserID INT,
    Status TINYINT,
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

INSERT INTO Roles (RoleName)
VALUES
    ('member'),
    ('admin'),
    ('artist');

select * from Roles;




drop table Payment
drop table Users
drop table Ranks
drop table Roles
drop table Account

