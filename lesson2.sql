create table customers(
	customer_id int,
	first_name varchar(40) not null,
	username varchar(40) not null unique,
	password varchar(40) not null unique,
	
	constraint customer_pk primary key (customer_id)
);

create sequence customers_seq owned by customers.customerid;

create table authorities(
	authority_id int,
	authority_name varchar(20) not null unique,
	
	constraint authority_pk primary key(authority_id) 
);

insert into authorities values(1, 'read');


create table customers_auths(
	customer_id int not null,
	authority_id int not null,
	
	constraint customers_authorities_pk primary key (customer_id, authority_id),
	constraint customers_auths_customers_fk
		foreign key(customer_id)
			references customers(customer_id),
	
	constraint customers_auths_authorities_fk
		foreign key(authority_id)
			references authorities(authority_id)
);

insert into customers_auths values(1, 1);



