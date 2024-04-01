-- Создание таблицы пользователей
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(20),
    is_admin BOOLEAN NOT NULL
);

-- Создание таблицы товаров
CREATE TABLE items (
   item_id SERIAL PRIMARY KEY,
   name VARCHAR(100) NOT NULL,
   price DECIMAL(10, 2) NOT NULL
);

-- Создание таблицы корзины пользователя
CREATE TABLE user_cart (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id) NOT NULL,
    item_id INT REFERENCES items(item_id) NOT NULL,
    quantity INT NOT NULL,
    CONSTRAINT fk_user_cart_user FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT fk_user_cart_item FOREIGN KEY (item_id) REFERENCES items(item_id)
);

-- Создание таблицы истории покупок
CREATE TABLE purchase_history (
   id SERIAL PRIMARY KEY,
   user_id INT REFERENCES users(user_id) NOT NULL,
   item_id INT REFERENCES items(item_id) NOT NULL,
    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_purchase_history_user FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT fk_purchase_history_item FOREIGN KEY (item_id) REFERENCES items(item_id)
);
