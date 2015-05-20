
CREATE SEQUENCE public.seq_produto_1;

CREATE TABLE public.produto (
                prd_id BIGINT NOT NULL,
                CONSTRAINT pk_produto PRIMARY KEY (prd_id)
);


ALTER SEQUENCE public.seq_produto_1;

CREATE SEQUENCE public.seq_product_backlog;

CREATE TABLE public.product_backlog (
                pbg_id BIGINT NOT NULL,
                prd_id BIGINT NOT NULL,
                CONSTRAINT pk_product_backlog PRIMARY KEY (pbg_id)
);


ALTER SEQUENCE public.seq_product_backlog;

CREATE SEQUENCE public.seq_item_backlog;

CREATE TABLE public.item_backlog (
                ibl_id BIGINT NOT NULL,
                pbg_id BIGINT NOT NULL,
                CONSTRAINT pk_item_backlog PRIMARY KEY (ibl_id)
);


ALTER SEQUENCE public.seq_item_backlog;

CREATE SEQUENCE public.seq_tarefa;

CREATE TABLE public.tarefa (
                trf_id BIGINT NOT NULL,
                ibl_id BIGINT NOT NULL,
                CONSTRAINT pk_tarefa PRIMARY KEY (trf_id)
);


ALTER SEQUENCE public.seq_tarefa;

CREATE SEQUENCE public.seq_projeto;

CREATE TABLE public.projeto (
                prj_id BIGINT NOT NULL,
                prd_id BIGINT NOT NULL,
                CONSTRAINT pk_projeto PRIMARY KEY (prj_id)
);


ALTER SEQUENCE public.seq_projeto;

CREATE SEQUENCE public.seq_release;

CREATE TABLE public.release (
                pk_release BIGINT NOT NULL,
                prj_id BIGINT NOT NULL,
                CONSTRAINT pk_release PRIMARY KEY (pk_release)
);


ALTER SEQUENCE public.seq_release;

CREATE SEQUENCE public.seq_sprint;

CREATE TABLE public.sprint (
                spt_id BIGINT NOT NULL,
                prj_id BIGINT NOT NULL,
                CONSTRAINT pk_sprint PRIMARY KEY (spt_id)
);


ALTER SEQUENCE public.seq_sprint;

CREATE SEQUENCE public.seq_permissao;

CREATE TABLE public.permissao (
                prm_id BIGINT NOT NULL,
                prm_dsc VARCHAR(30) NOT NULL,
                CONSTRAINT pk_permissao PRIMARY KEY (prm_id)
);


ALTER SEQUENCE public.seq_permissao;

CREATE SEQUENCE public.seq_papel;

CREATE TABLE public.papel (
                ppl_id BIGINT NOT NULL,
                ppl_dsc VARCHAR(30) NOT NULL,
                CONSTRAINT pk_papel PRIMARY KEY (ppl_id)
);


ALTER SEQUENCE public.seq_papel;

CREATE TABLE public.papel_permissao (
                ppl_id BIGINT NOT NULL,
                prm_id BIGINT NOT NULL,
                CONSTRAINT pk_papel_permissao PRIMARY KEY (ppl_id, prm_id)
);


CREATE SEQUENCE public.seq_usuario;

CREATE TABLE public.usuario (
                usr_id BIGINT NOT NULL,
                usr_nme VARCHAR(50) NOT NULL,
                usr_lgn VARCHAR(20) NOT NULL,
                usr_sen VARCHAR(100) NOT NULL,
                CONSTRAINT pk_usuario PRIMARY KEY (usr_id)
);


ALTER SEQUENCE public.seq_usuario;

CREATE UNIQUE INDEX uq_usuario_usr_lgn
 ON public.usuario
 ( usr_lgn );

CREATE TABLE public.usuario_projeto (
                usr_id BIGINT NOT NULL,
                prj_id BIGINT NOT NULL,
                CONSTRAINT pk_usuario_projeto PRIMARY KEY (usr_id, prj_id)
);


CREATE TABLE public.usuario_papel_projeto (
                usr_id BIGINT NOT NULL,
                prj_id BIGINT NOT NULL,
                ppl_id BIGINT NOT NULL,
                CONSTRAINT pk_usuario_papel_projeto PRIMARY KEY (usr_id, prj_id, ppl_id)
);


ALTER TABLE public.projeto ADD CONSTRAINT produto_projeto_fk
FOREIGN KEY (prd_id)
REFERENCES public.produto (prd_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.product_backlog ADD CONSTRAINT produto_product_backlog_fk
FOREIGN KEY (prd_id)
REFERENCES public.produto (prd_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.item_backlog ADD CONSTRAINT product_backlog_item_backlog_fk
FOREIGN KEY (pbg_id)
REFERENCES public.product_backlog (pbg_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.tarefa ADD CONSTRAINT item_backlog_tarefa_fk
FOREIGN KEY (ibl_id)
REFERENCES public.item_backlog (ibl_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.usuario_projeto ADD CONSTRAINT projeto_usuario_projeto_fk
FOREIGN KEY (prj_id)
REFERENCES public.projeto (prj_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.sprint ADD CONSTRAINT projeto_sprint_fk
FOREIGN KEY (prj_id)
REFERENCES public.projeto (prj_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.release ADD CONSTRAINT projeto_release_fk
FOREIGN KEY (prj_id)
REFERENCES public.projeto (prj_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.papel_permissao ADD CONSTRAINT permissao_papel_permissao_fk
FOREIGN KEY (prm_id)
REFERENCES public.permissao (prm_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.papel_permissao ADD CONSTRAINT papel_papel_permissao_fk
FOREIGN KEY (ppl_id)
REFERENCES public.papel (ppl_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.usuario_papel_projeto ADD CONSTRAINT papel_usuario_papel_projeto_fk
FOREIGN KEY (ppl_id)
REFERENCES public.papel (ppl_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.usuario_projeto ADD CONSTRAINT usuario_usuario_projeto_fk
FOREIGN KEY (usr_id)
REFERENCES public.usuario (usr_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE public.usuario_papel_projeto ADD CONSTRAINT usuario_projeto_usuario_papel_projeto_fk
FOREIGN KEY (usr_id, prj_id)
REFERENCES public.usuario_projeto (usr_id, prj_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;