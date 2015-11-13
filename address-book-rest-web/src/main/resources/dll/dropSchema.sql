alter table addresses drop constraint FK34207BA2FBFE6130;
alter table addresses drop constraint FK34207BA27EE00646;
alter table federalstates drop constraint FK1A5486DFAE853FD9;
alter table zipcodes drop constraint FKF88385A5AE853FD9;
drop table addresses;
drop table countries;
drop table federalstates;
drop table zipcodes;
drop sequence hibernate_sequence;
