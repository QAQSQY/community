create table comment
(
	id bigint auto_increment,
	parent_id bigint not null,
	content varchar(255),
	type int not null,
	commentator int not null,
	gmt_create bigint not null,
	gmt_modified bigint not null,
	like_count bigint default 0 not null,
	constraint COMMENT_PK
		primary key (id)
);
comment on column comment.type is '父类类型';
comment on column comment.commentator is '评论人id';
comment on column comment.gmt_create is '创建时间';
comment on column comment.gmt_modified is '更新时间';
comment on column comment.content is '评论内容';