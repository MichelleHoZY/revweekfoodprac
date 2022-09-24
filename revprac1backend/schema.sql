drop schema if exists food_orders;

create schema food_orders;

use food_orders;

create table customer_info(
    name varchar(64) not null,
    mobile char(8),
    time char(6),
    order_id char(8) not null,

    primary key(order_id)
);

create table orders(
    order_id char(8) not null,
    item varchar(64) not null,
    quantity char(2) not null,

    primary key(order_id),
    constraint fk_order_id
    foreign key (order_id) references customer_info(order_id)
);