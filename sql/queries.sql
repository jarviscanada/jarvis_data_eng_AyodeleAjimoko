-- Introduction

--- SQL Queries

--- Table Setup (DDL)

CREATE TABLE cd.members(
        memid integer NOT NULL,
        surname character varying(200) not null,
        firstname character varying(200) not null,
        address character varying(300) not null,
        zipcode integer not null,
        telephone character varying(20) not null,
        recommendedby integer,
        joindate timestamp not null,

        constraint members_pk primary key(memid),
        constraint fk_members_recommendedby foreign key(recommendedby)
        references cd.members(memid) on delete set null
);


CREATE TABLE cd.bookings(
        bookid integer not null,
        facid integer not null,
        memid integer not null,
        starttime timestamp not null,
        slots integer not null,

        constraint bookings_pk primary key (bookid),
        constraint fk_bookings_facid foreign key (facid) REFERENCES cd.facilities(facid),
    constraint fk_bookings_memid foreign key (memid) REFERENCES cd.members(memid)
);


CREATE TABLE cd.facilities(
        facid integer not null,
        name character varying(100) not null,
        membercost numeric not null,
        guestcost numeric not null,
        initialoutlay numeric not null,
        monthlymaintenance numeric not null,

        constraint facilities_pk primary key (facid)

);


--- MODIFYING DATA

--- Insert 1
INSERT INTO cd.facilities
            (facid,
             name,
             membercost,
             guestcost,
             initialoutlay,
             monthlymaintenance)
VALUES      (9,
             'Spa',
             20,
             30,
             100000,
             800);

--- Insert 3 - Select in Insert

insert into cd.facilities (
  facid, name, membercost, guestcost,
  initialoutlay, monthlymaintenance
)
select
  (
    select
      max(facid)
    from
      cd.facilities
  )+ 1,
  'Spa',
  20,
  30,
  100000,
  800;

-- Update

update
  cd.facilities
set
  initialoutlay = 10000
where
  facid = 1;


--- Update with Calculation


update
  cd.facilities facs
set
  membercost =(
    select
      membercost1.1
    from
      cd.facilities
    where
      facid = 0
  ),
  guestcost = (
    select
      guestcost1.1
    from
      cd.facilities
    where
      facid = 0
  )
where
  facs.facid = 1;

--- Delete all
delete from cd.bookings

-- Delete Condition

delete from cd.members where memid = 37;


-- Where 2 - Basic

select
  facid,
  name,
  membercost,
  monthlymaintenance
from
  cd.facilities
where
  membercost > 0
  and membercost < (monthlymaintenance / 50);

-- Where 3 
select
  *
from
  cd.facilities
where
  name like '%Tennis%'

--  Where 4
select
  *
from
  cd.facilities
where
  facid in (1, 5)


-- Date
select
  memid,
  surname,
  firstname,
  joindate
from
  cd.members
where
  joindate >= '2012-09-01'

-- Union

select surname
         from cd.members

union


select name
        from cd.facilities

--- JOIN QUESTIONS ###

-- Simple Join
select
  starttime
from
  cd.bookings
  inner join cd.members on cd.members.memid = cd.bookings.memid
where
  cd.members.firstname = 'David'
  and cd.members.surname = 'Farrell';


-- Simple Join 2
select
  starttime,
  name
from
  cd.bookings
  inner join cd.facilities on cd.bookings.facid = cd.facilities.facid
where
  cd.facilities.name in (
    'Tennis Court 1', 'Tennis Court 2'
  )
  and cd.bookings.starttime >= '2012-09-21'
  and cd.bookings.starttime < '2012-09-22'
order by
  cd.bookings.starttime


-- Three Joins 1
select
  mems.firstname as memfname,
  mems.surname as memsname,
  recs.firstname as recfname,
  recs.surname as recsname
from
  cd.members mems
  left outer join cd.members recs on recs.memid = mems.recommendedby
order by
  memsname,
  memfname;

-- Three Jobs 2

select
  distinct recs.firstname as firstname,
  recs.surname as surname
from
  cd.members mems
  inner join cd.members recs on recs.memid = mems.recommendedby
order by
  surname,
  firstname;

-- Subquery and Join
select distinct mems.firstname || ' ' ||  mems.surname as member,
        (select recs.firstname || ' ' || recs.surname as recommender
                from cd.members recs
                where recs.memid = mems.recommendedby
        )
        from
                cd.members mems
order by member;


-- AGGREGATION

-- Group by, Order by

select
  recommendedby,
  count(*) 
from 
  cd.members 
where 
  recommendedby is not null 
group by 
  recommendedby 
order by 
  recommendedby

-- Fachours - Group By, Order By
select
  sum(slots) as "Total Slots" 
from 
  cd.bookings 
group by 
  facid 
order by 
  facid;

-- Fachours By Month - Group By, With Condition
select 
  facid, 
  sum(slots) as "Total Slots" 
from 
  cd.bookings 
where 
  starttime >= '2012-09-01' 
  and starttime < '2012-10-01' 
group by 
  facid 
order by 
  sum(slots)

--  Fachours By Month 2 - Group By Multi Column
select 
  facid, 
  extract(
    month
    from
      starttime
  ) as month, 
  sum(slots) as "Total Slots" 
from 
  cd.bookings 
where 
  extract(
    year
    from
      starttime
  ) = 2012 
group by 
  facid, 
  month 
order by 
  facid, 
  month

-- Members - Count Distinct
select 
  count(distinct memid) as "count" 
from 
  cd.bookings

-- NBooking - Group By Multiple Columns, Join
select 
  mems.surname, 
  mems.firstname, 
  mems.memid, 
  min(bks.starttime) as starttime 
from 
  cd.bookings bks 
  inner join cd.members mems on mems.memid = bks.memid 
where 
  starttime > '2012-09-01' 
group by 
  mems.surname, 
  mems.firstname, 
  mems.memid 
order by 
  mems.memid

-- Count Members - Window Function

select 
  (
    select
      count(*)
    from
      cd.members
  ) as count,
  firstname,
  surname
from
  cd.members
order by
  joindate



-- NumMembers - Window Function

select
  row_number() over(
    order by
      joindate
  ),
  firstname,
  surname
from
  cd.members
order by
  joindate


-- Fachours 4 - Window Function, Subquery, Group By
select
  facid,
  total
from
  (
    select
      facid,
      sum(slots) total,
      rank() over (
        order by
          sum(slots) desc
      ) rank
    from
      cd.bookings
    group by
      facid
  ) as ranked
where
  rank = 1


-- STRING

-- Concat - Format String
select
  surname || ', ' || firstname as name
from
  cd.members

-- Reg - Where + String Function

select
  memid,
  telephone
from
  cd.members
where
  telephone ~ '[()]';

-- Substr - Group By
select
  substr (mems.surname, 1, 1) as letter,
  count(*) as count 
from 
  cd.members mems 
group by 
  letter 
order by 
  letter

