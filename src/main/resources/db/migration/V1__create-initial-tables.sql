create table tb_address (
    id uuid default gen_random_uuid(),
    address varchar(255),
    city varchar(255),
    complement varchar(255),
    country varchar(255),
    state varchar(255),
    zip_code varchar(255),
    primary key (id)
);

create table tb_cart (
    total_price numeric(38,2),
    id uuid default gen_random_uuid(),
    primary key (id)
);

create table tb_category (
    id uuid default gen_random_uuid(),
    name varchar(255),
    primary key (id)
);

create table tb_product (
    price numeric(38,2),
    quantity integer,
    category_id uuid,
    id uuid default gen_random_uuid(),
    description text,
    image_url text,
    name text,
    primary key (id),
    foreign key (category_id) references tb_category (id)
);

create table tb_cart_item (
    discount float(53) not null,
    price float(53) not null,
    quantity integer,
    cart_id uuid,
    id uuid default gen_random_uuid(),
    product_id uuid,
    primary key (id),
    foreign key (cart_id) references tb_cart (id),
    foreign key (product_id) references tb_product (id)
);

create table tb_category_products (
    category_id uuid not null,
    products_id uuid not null unique,
    primary key (category_id, products_id),
    foreign key (category_id) references tb_category (id),
    foreign key (products_id) references tb_product (id)
);

create table tb_order (
    instant timestamp(6) with time zone,
    id uuid default gen_random_uuid(),
    shipping_address_id uuid,
    primary key (id),
    foreign key (shipping_address_id) references tb_address (id)
);

create table tb_order_item (
    price numeric(38,2),
    quantity integer,
    order_id uuid not null,
    product_id uuid not null,
    primary key (order_id, product_id),
    foreign key (product_id) references tb_product (id),
    foreign key (order_id) references tb_order (id)
);
