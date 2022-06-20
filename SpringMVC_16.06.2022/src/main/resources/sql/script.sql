alter table if exists transaction_deposit_expenses
    drop constraint if exists transaction_deposit_expenses_deposit_fk;
alter table if exists transaction_deposit_expenses
    drop constraint if exists transaction_deposit_expenses_expenses_fk;
alter table if exists transaction_income_deposit
    drop constraint if exists transaction_income_deposit_deposit_fk;
alter table if exists transaction_income_deposit
    drop constraint if exists transaction_income_deposit_income_fk;

drop table if exists deposit cascade;
drop table if exists expenses cascade;
drop table if exists income cascade;
drop table if exists transaction_deposit_expenses cascade;
drop table if exists transaction_income_deposit cascade;

create table deposit
(
    id    int8 generated ALWAYS as identity,
    money int8,
    name  varchar(255),
    primary key (id)
);
create table expenses
(
    id         int8 generated ALWAYS as identity,
    fact_money int8,
    name       varchar(255),
    plan_money int8,
    primary key (id)
);
create table income
(
    id         int8 generated ALWAYS as identity,
    fact_money int8,
    name       varchar(255),
    plan_money int8,
    primary key (id)
);
create table transaction_deposit_expenses
(
    id          int8 generated ALWAYS as identity,
    date        date,
    money       int8,
    id_deposit  int8,
    id_expenses int8,
    primary key (id)
);
create table transaction_income_deposit
(
    id         int8 generated ALWAYS as identity,
    date       date,
    money      int8,
    id_deposit int8,
    id_income  int8,
    primary key (id)
);
alter table if exists transaction_deposit_expenses
    add constraint transaction_deposit_expenses_deposit_fk foreign key (id_deposit) references deposit;
alter table if exists transaction_deposit_expenses
    add constraint transaction_deposit_expenses_expenses_fk foreign key (id_expenses) references expenses;
alter table if exists transaction_income_deposit
    add constraint transaction_income_deposit_deposit_fk foreign key (id_deposit) references deposit;
alter table if exists transaction_income_deposit
    add constraint transaction_income_deposit_income_fk foreign key (id_income) references income;

insert into public.income (fact_money, name, plan_money)
values  (30000, 'work', 30000),
        (2000, 'deposit', 2000);

insert into public.deposit (money, name)
values  (0, 'cash'),
        (0, 'credit card');

insert into public.expenses (fact_money, name, plan_money)
values  (2000, 'auto', 3000),
        (30000, 'food', 8000);

insert into public.transaction_income_deposit (date, money, id_deposit, id_income)
values  ('2022-05-01', 15000, 2, 1),
        ('2022-05-15', 15000, 2, 1),
        ('2022-05-05', 2000, 1, 2);

insert into public.transaction_deposit_expenses (date, money, id_deposit, id_expenses)
values  ('2022-05-01', 1000, 2, 2),
        ('2022-05-02', 1000, 2, 2),
        ('2022-05-03', 1000, 2, 2),
        ('2022-05-04', 1000, 2, 2),
        ('2022-05-05', 1000, 2, 2),
        ('2022-05-06', 1000, 2, 2),
        ('2022-05-07', 2000, 1, 1),
        ('2022-05-08', 1000, 2, 2),
        ('2022-05-09', 1000, 2, 2),
        ('2022-05-10', 1000, 2, 2),
        ('2022-05-11', 1000, 2, 2),
        ('2022-05-12', 1000, 2, 2),
        ('2022-05-13', 1000, 2, 2),
        ('2022-05-14', 1000, 2, 2),
        ('2022-05-15', 1000, 2, 2),
        ('2022-05-16', 1000, 2, 2),
        ('2022-05-17', 1000, 2, 2),
        ('2022-05-18', 1000, 2, 2),
        ('2022-05-19', 1000, 2, 2),
        ('2022-05-20', 1000, 2, 2),
        ('2022-05-21', 1000, 2, 2),
        ('2022-05-22', 1000, 2, 2),
        ('2022-05-23', 1000, 2, 2),
        ('2022-05-24', 1000, 2, 2),
        ('2022-05-25', 1000, 2, 2),
        ('2022-05-26', 1000, 2, 2),
        ('2022-05-27', 1000, 2, 2),
        ('2022-05-28', 1000, 2, 2),
        ('2022-05-29', 1000, 2, 2),
        ('2022-05-30', 1000, 2, 2),
        ('2022-05-31', 1000, 2, 2);
