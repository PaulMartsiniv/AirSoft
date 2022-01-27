create table if not exists air_companies
(
    id bigint auto_increment
        primary key,
    company_type int null,
    founded_at date null,
    name varchar(255) null
);

create table if not exists airplanes
(
    id bigint auto_increment
        primary key,
    created_at date null,
    factory_serial_number varchar(255) null,
    flight_distance int null,
    fuel_capacity int null,
    name varchar(255) null,
    number_of_flights int null,
    type int null,
    air_company_id bigint null,
    constraint FKsnx5fllu72d38u8e7cghe6ofq
        foreign key (air_company_id) references air_companies (id)
);

create table if not exists flights
(
    id bigint auto_increment
        primary key,
    created_at date null,
    delay_started_at datetime(6) null,
    departure_country varchar(255) null,
    destination_country varchar(255) null,
    distance int not null,
    ended_at datetime(6) null,
    estimated_flight_time time null,
    flight_status int null,
    started_at datetime(6) null,
    air_company_id bigint null,
    constraint FK4dg00qjfx5f8hw79i6ebdhluu
        foreign key (air_company_id) references air_companies (id)
);

create table if not exists airplane_flight
(
    airplane_id bigint not null,
    flight_id bigint not null,
    constraint FKja17ax9n19afffwwy1pxeu67a
        foreign key (flight_id) references airplanes (id),
    constraint FKjc7qbtcorjc0tcl93eap1gox5
        foreign key (airplane_id) references flights (id)
);

