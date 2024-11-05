-- Sử dụng cơ sở dữ liệu FOODIE
use master
go
CREATE DATABASE FOODIE;
GO
USE FOODIE;
GO

-- Tạo bảng Roles để quản lý các vai trò
CREATE TABLE Roles (
    RoleId INT IDENTITY(1,1) PRIMARY KEY,
    RoleName NVARCHAR(50) NOT NULL UNIQUE
);
GO
--ok

-- Thêm dữ liệu vào bảng Roles
INSERT INTO Roles (RoleName) VALUES ('Customer');
INSERT INTO Roles (RoleName) VALUES ('Saler');
INSERT INTO Roles (RoleName) VALUES ('Shipper');
INSERT INTO Roles (RoleName) VALUES ('Admin');
GO

-- Tạo bảng Users
CREATE TABLE Users (
    UserId INT IDENTITY(1,1) PRIMARY KEY,
    FirstName NVARCHAR(100) NOT NULL,
    LastName NVARCHAR(100) NOT NULL,
    PhoneNumber NVARCHAR(20) NOT NULL UNIQUE,
    Email NVARCHAR(100) NULL UNIQUE,
    PasswordHash NVARCHAR(255) NOT NULL,
    [Address] NVARCHAR(255),
    CreateAt DATETIME DEFAULT GETDATE(),
	UpDateAt DATETIME DEFAULT GETDATE(),
    AvatarUrl NVARCHAR(255),
    RoleId INT,
    FOREIGN KEY (RoleId) REFERENCES Roles(RoleId)
);
GO

-- Tạo bảng Restaurants
CREATE TABLE Restaurants (
    RestaurantId INT IDENTITY(1,1) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL,
    Location NVARCHAR(255),
    PhoneNumber NVARCHAR(20),
    TimeOpen Time not null,
	TimeClose Time not null,
    CreateAt DATETIME DEFAULT GETDATE(),
	UpDateAt DATETIME DEFAULT GETDATE(),
    ManagerId INT,
    AvatarUrl NVARCHAR(255), -- Ảnh đại diện của nhà hàng
    CoverImageUrl NVARCHAR(255), -- Ảnh bìa của nhà hàng
	Status INT NOT NULL DEFAULT  0,
    FOREIGN KEY (ManagerId) REFERENCES Users(UserId)
);
GO

-- Tạo bảng CategoryProduct để quản lý các danh mục sản phẩm
CREATE TABLE CategoryProduct (
    CategoryId INT IDENTITY(1,1) PRIMARY KEY,
    CategoryName NVARCHAR(100) NOT NULL UNIQUE
);
GO

-- Tạo bảng Product để quản lý các sản phẩm
CREATE TABLE Products (
    ProductId INT IDENTITY(1,1) PRIMARY KEY,
    RestaurantId INT NOT NULL,
    CategoryId INT NOT NULL,
    Name NVARCHAR(100) NOT NULL,
    Description NVARCHAR(255),
    Price DECIMAL(10,2) NOT NULL,
	CreateAt DATETIME DEFAULT GETDATE(),
	UpDateAt DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (RestaurantId) REFERENCES Restaurants(RestaurantId),
    FOREIGN KEY (CategoryId) REFERENCES CategoryProduct(CategoryId)
);
GO

-- Tạo bảng ProductImages để quản lý các hình ảnh của sản phẩm
CREATE TABLE ProductImages (
    ImageId INT IDENTITY(1,1) PRIMARY KEY,
    ProductId INT NOT NULL,
    ImageUrl NVARCHAR(255) NOT NULL,
	OrderIndex INT Not null,
    FOREIGN KEY (ProductId) REFERENCES Products(ProductId)
);
GO

-- Tạo bảng ProductFeedback để quản lý phản hồi của sản phẩm
CREATE TABLE ProductFeedbacks (
    FeedbackId INT IDENTITY(1,1) PRIMARY KEY,
    ProductId INT NOT NULL,
    UserId INT NOT NULL,
    Rating INT CHECK (Rating >= 1 AND Rating <= 5),
    Comment NVARCHAR(1000),
    CreatedAt DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (ProductId) REFERENCES Products(ProductId),
    FOREIGN KEY (UserId) REFERENCES Users(UserId)
);
GO

-- Tạo bảng FavoriteProducts để quản lý sản phẩm yêu thích của khách hàng
CREATE TABLE FavoriteProducts (
    UserId INT NOT NULL,
    ProductId INT NOT NULL,
    CreatedAt DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (UserId) REFERENCES Users(UserId),
    FOREIGN KEY (ProductId) REFERENCES Products(ProductId),
    PRIMARY KEY (UserId, ProductId)
);
GO

-- Tạo bảng Orders để quản lý các đơn hàng
CREATE TABLE Orders (
    OrderId INT IDENTITY(1,1) PRIMARY KEY,
    UserId INT NOT NULL,
    RestaurantId INT NOT NULL,
    OrderDate DATETIME DEFAULT GETDATE(),
    TotalAmount DECIMAL(10,2) NOT NULL,
    Status NVARCHAR(50),
    FOREIGN KEY (UserId) REFERENCES Users(UserId),
    FOREIGN KEY (RestaurantId) REFERENCES Restaurants(RestaurantId)
);
GO

-- Tạo bảng OrderItems để quản lý các sản phẩm trong đơn hàng
CREATE TABLE OrderItems (
    OrderItemId INT IDENTITY(1,1) PRIMARY KEY,
    OrderId INT NOT NULL,
    ProductId INT NOT NULL,
    Quantity INT NOT NULL,
    Price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (OrderId) REFERENCES Orders(OrderId),
    FOREIGN KEY (ProductId) REFERENCES Products(ProductId)
);
GO

-- Tạo bảng UserLocations để quản lý vị trí người dùng
CREATE TABLE UserLocations (
    LocationId INT IDENTITY(1,1) PRIMARY KEY,
    UserId INT NOT NULL,
    Latitude DECIMAL(9,6) NOT NULL, -- Lưu vĩ độ với độ chính xác 6 chữ số sau dấu phẩy
    Longitude DECIMAL(9,6) NOT NULL, -- Lưu kinh độ với độ chính xác 6 chữ số sau dấu phẩy
    UpdatedAt DATETIME DEFAULT GETDATE(), -- Thời gian cập nhật vị trí
    FOREIGN KEY (UserId) REFERENCES Users(UserId)
);
GO

-- Tạo bảng RestaurantLocations để quản lý vị trí nhà hàng
CREATE TABLE RestaurantLocations (
    LocationId INT IDENTITY(1,1) PRIMARY KEY,
    RestaurantId INT NOT NULL,
    Latitude DECIMAL(9,6) NOT NULL, -- Lưu vĩ độ với độ chính xác 6 chữ số sau dấu phẩy
    Longitude DECIMAL(9,6) NOT NULL, -- Lưu kinh độ với độ chính xác 6 chữ số sau dấu phẩy
    UpdatedAt DATETIME DEFAULT GETDATE(), -- Thời gian cập nhật vị trí
    FOREIGN KEY (RestaurantId) REFERENCES Restaurants(RestaurantId)
);
GO

-- Tạo bảng Cart để quản lý giỏ hàng của người dùng
CREATE TABLE Carts (
    CartId INT IDENTITY(1,1) PRIMARY KEY,
    UserId INT NOT NULL,
    CreatedAt DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (UserId) REFERENCES Users(UserId)
);
GO

-- Tạo bảng CartItem để quản lý các sản phẩm trong giỏ hàng
CREATE TABLE CartItems (
    CartItemId INT IDENTITY(1,1) PRIMARY KEY,
    CartId INT NOT NULL,
    ProductId INT NOT NULL,
    Quantity INT NOT NULL,
    Price DECIMAL(10,2) NOT NULL,
	CreateAt DATETIME DEFAULT GETDATE(),
	UpDateAt DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (CartId) REFERENCES Carts(CartId),
    FOREIGN KEY (ProductId) REFERENCES Products(ProductId)
);
GO

-- Tạo bảng Invoices để quản lý các hóa đơn
CREATE TABLE Invoices (
    InvoiceId INT IDENTITY(1,1) PRIMARY KEY, -- ID hóa đơn
    OrderId INT NOT NULL, -- ID đơn hàng liên quan
    InvoiceDate DATETIME DEFAULT GETDATE(), -- Ngày lập hóa đơn
    TotalAmount DECIMAL(10,2) NOT NULL, -- Số tiền tổng cộng
    Status NVARCHAR(50), -- Trạng thái hóa đơn
    FOREIGN KEY (OrderId) REFERENCES Orders(OrderId) -- Khóa ngoại liên kết với bảng Orders
);
GO

