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
values  (3000000, 'work', 3000000),
        (0, 'deposit', 2000);

insert into public.deposit (money, name)
values  (0, 'cash'),
        (0, 'credit card');

insert into public.expenses (fact_money, name, plan_money)
values  (200000, 'auto', 300000),
        (3000000, 'food', 800000);

insert into public.transaction_income_deposit (date, money, id_deposit, id_income)
values  ('2022-05-01', 1500000, 1, 2),
        ('2022-05-15', 1500000, 1, 2),
        ('2022-05-05', 200000, 2, 1);

insert into public.transaction_deposit_expenses (date, money, id_deposit, id_expenses)
values  ('2022-05-01', 100000, 2, 2),
        ('2022-05-02', 100000, 2, 2),
        ('2022-05-03', 100000, 2, 2),
        ('2022-05-04', 100000, 2, 2),
        ('2022-05-05', 100000, 2, 2),
        ('2022-05-06', 100000, 2, 2),
        ('2022-05-07', 200000, 1, 1),
        ('2022-05-08', 100000, 2, 2),
        ('2022-05-09', 100000, 2, 2),
        ('2022-05-10', 100000, 2, 2),
        ('2022-05-11', 100000, 2, 2),
        ('2022-05-12', 100000, 2, 2),
        ('2022-05-13', 100000, 2, 2),
        ('2022-05-14', 100000, 2, 2),
        ('2022-05-15', 100000, 2, 2),
        ('2022-05-16', 100000, 2, 2),
        ('2022-05-17', 100000, 2, 2),
        ('2022-05-18', 100000, 2, 2),
        ('2022-05-19', 100000, 2, 2),
        ('2022-05-20', 100000, 2, 2),
        ('2022-05-21', 100000, 2, 2),
        ('2022-05-22', 100000, 2, 2),
        ('2022-05-23', 100000, 2, 2),
        ('2022-05-24', 100000, 2, 2),
        ('2022-05-25', 100000, 2, 2),
        ('2022-05-26', 100000, 2, 2),
        ('2022-05-27', 100000, 2, 2),
        ('2022-05-28', 100000, 2, 2),
        ('2022-05-29', 100000, 2, 2),
        ('2022-05-30', 100000, 2, 2),
        ('2022-05-31', 100000, 2, 2);
