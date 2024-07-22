--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3
-- Dumped by pg_dump version 16.3

-- Started on 2024-07-22 19:32:08

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4904 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 232 (class 1259 OID 16551)
-- Name: comments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comments (
    id integer NOT NULL,
    community_id integer,
    user_id integer,
    content text NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.comments OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 16550)
-- Name: comments_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.comments_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.comments_id_seq OWNER TO postgres;

--
-- TOC entry 4905 (class 0 OID 0)
-- Dependencies: 231
-- Name: comments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.comments_id_seq OWNED BY public.comments.id;


--
-- TOC entry 230 (class 1259 OID 16535)
-- Name: community; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.community (
    id integer NOT NULL,
    user_id integer,
    title character varying(100) NOT NULL,
    content text NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.community OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16534)
-- Name: community_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.community_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.community_id_seq OWNER TO postgres;

--
-- TOC entry 4906 (class 0 OID 0)
-- Dependencies: 229
-- Name: community_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.community_id_seq OWNED BY public.community.id;


--
-- TOC entry 228 (class 1259 OID 16523)
-- Name: global_changes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.global_changes (
    id integer NOT NULL,
    title character varying(100) NOT NULL,
    description text,
    impact jsonb,
    reported_at timestamp without time zone NOT NULL,
    impact_type character varying(20) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT global_changes_impact_type_check CHECK (((impact_type)::text = ANY ((ARRAY['POSITIVE'::character varying, 'NEGATIVE'::character varying, 'NO_DIFFERENCE'::character varying])::text[])))
);


ALTER TABLE public.global_changes OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16522)
-- Name: global_changes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.global_changes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.global_changes_id_seq OWNER TO postgres;

--
-- TOC entry 4907 (class 0 OID 0)
-- Dependencies: 227
-- Name: global_changes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.global_changes_id_seq OWNED BY public.global_changes.id;


--
-- TOC entry 220 (class 1259 OID 16459)
-- Name: organizations; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organizations (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    description text,
    industry character varying(100),
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.organizations OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16458)
-- Name: organizations_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.organizations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.organizations_id_seq OWNER TO postgres;

--
-- TOC entry 4908 (class 0 OID 0)
-- Dependencies: 219
-- Name: organizations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.organizations_id_seq OWNED BY public.organizations.id;


--
-- TOC entry 222 (class 1259 OID 16472)
-- Name: sustainability_metrics; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sustainability_metrics (
    id integer NOT NULL,
    user_id integer,
    organization_id integer,
    metric_name character varying(50) NOT NULL,
    metric_type character varying(20) NOT NULL,
    metric_value numeric(10,2),
    metric_category character varying(50),
    measured_at timestamp without time zone NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_metric CHECK (((((metric_type)::text = 'NUMERICAL'::text) AND (metric_value IS NOT NULL) AND (metric_category IS NULL)) OR (((metric_type)::text = 'CATEGORICAL'::text) AND (metric_category IS NOT NULL) AND (metric_value IS NULL)))),
    CONSTRAINT sustainability_metrics_metric_type_check CHECK (((metric_type)::text = ANY ((ARRAY['NUMERICAL'::character varying, 'CATEGORICAL'::character varying])::text[])))
);


ALTER TABLE public.sustainability_metrics OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16471)
-- Name: sustainability_metrics_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sustainability_metrics_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.sustainability_metrics_id_seq OWNER TO postgres;

--
-- TOC entry 4909 (class 0 OID 0)
-- Dependencies: 221
-- Name: sustainability_metrics_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.sustainability_metrics_id_seq OWNED BY public.sustainability_metrics.id;


--
-- TOC entry 224 (class 1259 OID 16493)
-- Name: tips_challenges; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tips_challenges (
    id integer NOT NULL,
    title character varying(100) NOT NULL,
    description text,
    type character varying(20) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.tips_challenges OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16492)
-- Name: tips_challenges_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tips_challenges_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tips_challenges_id_seq OWNER TO postgres;

--
-- TOC entry 4910 (class 0 OID 0)
-- Dependencies: 223
-- Name: tips_challenges_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tips_challenges_id_seq OWNED BY public.tips_challenges.id;


--
-- TOC entry 218 (class 1259 OID 16427)
-- Name: user_profiles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_profiles (
    id integer NOT NULL,
    user_id integer,
    display_name character varying(100),
    bio text,
    profile_picture character varying(255),
    city character varying(100),
    country character varying(100),
    pincode character varying(20),
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.user_profiles OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16426)
-- Name: user_profiles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_profiles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.user_profiles_id_seq OWNER TO postgres;

--
-- TOC entry 4911 (class 0 OID 0)
-- Dependencies: 217
-- Name: user_profiles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_profiles_id_seq OWNED BY public.user_profiles.id;


--
-- TOC entry 226 (class 1259 OID 16504)
-- Name: user_progress; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_progress (
    id integer NOT NULL,
    user_id integer,
    tip_challenge_id integer,
    status character varying(20) NOT NULL,
    started_at timestamp without time zone,
    completed_at timestamp without time zone,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.user_progress OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16503)
-- Name: user_progress_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_progress_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.user_progress_id_seq OWNER TO postgres;

--
-- TOC entry 4912 (class 0 OID 0)
-- Dependencies: 225
-- Name: user_progress_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_progress_id_seq OWNED BY public.user_progress.id;


--
-- TOC entry 216 (class 1259 OID 16412)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    first_name character varying(50),
    last_name character varying(50),
    contact_number character varying(15),
    active_status character(1) NOT NULL,
    role character varying(20) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT users_active_status_check CHECK ((active_status = ANY (ARRAY['Y'::bpchar, 'N'::bpchar]))),
    CONSTRAINT users_email_check CHECK (((email)::text ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'::text))
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16411)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 4913 (class 0 OID 0)
-- Dependencies: 215
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 4698 (class 2604 OID 16554)
-- Name: comments id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments ALTER COLUMN id SET DEFAULT nextval('public.comments_id_seq'::regclass);


--
-- TOC entry 4695 (class 2604 OID 16538)
-- Name: community id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.community ALTER COLUMN id SET DEFAULT nextval('public.community_id_seq'::regclass);


--
-- TOC entry 4692 (class 2604 OID 16526)
-- Name: global_changes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.global_changes ALTER COLUMN id SET DEFAULT nextval('public.global_changes_id_seq'::regclass);


--
-- TOC entry 4680 (class 2604 OID 16462)
-- Name: organizations id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organizations ALTER COLUMN id SET DEFAULT nextval('public.organizations_id_seq'::regclass);


--
-- TOC entry 4683 (class 2604 OID 16475)
-- Name: sustainability_metrics id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sustainability_metrics ALTER COLUMN id SET DEFAULT nextval('public.sustainability_metrics_id_seq'::regclass);


--
-- TOC entry 4686 (class 2604 OID 16496)
-- Name: tips_challenges id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tips_challenges ALTER COLUMN id SET DEFAULT nextval('public.tips_challenges_id_seq'::regclass);


--
-- TOC entry 4677 (class 2604 OID 16430)
-- Name: user_profiles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_profiles ALTER COLUMN id SET DEFAULT nextval('public.user_profiles_id_seq'::regclass);


--
-- TOC entry 4689 (class 2604 OID 16507)
-- Name: user_progress id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_progress ALTER COLUMN id SET DEFAULT nextval('public.user_progress_id_seq'::regclass);


--
-- TOC entry 4674 (class 2604 OID 16415)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 4898 (class 0 OID 16551)
-- Dependencies: 232
-- Data for Name: comments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.comments (id, community_id, user_id, content, created_at, updated_at) FROM stdin;
\.


--
-- TOC entry 4896 (class 0 OID 16535)
-- Dependencies: 230
-- Data for Name: community; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.community (id, user_id, title, content, created_at, updated_at) FROM stdin;
\.


--
-- TOC entry 4894 (class 0 OID 16523)
-- Dependencies: 228
-- Data for Name: global_changes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.global_changes (id, title, description, impact, reported_at, impact_type, created_at, updated_at) FROM stdin;
\.


--
-- TOC entry 4886 (class 0 OID 16459)
-- Dependencies: 220
-- Data for Name: organizations; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.organizations (id, name, description, industry, created_at, updated_at) FROM stdin;
\.


--
-- TOC entry 4888 (class 0 OID 16472)
-- Dependencies: 222
-- Data for Name: sustainability_metrics; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sustainability_metrics (id, user_id, organization_id, metric_name, metric_type, metric_value, metric_category, measured_at, created_at, updated_at) FROM stdin;
\.


--
-- TOC entry 4890 (class 0 OID 16493)
-- Dependencies: 224
-- Data for Name: tips_challenges; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tips_challenges (id, title, description, type, created_at, updated_at) FROM stdin;
\.


--
-- TOC entry 4884 (class 0 OID 16427)
-- Dependencies: 218
-- Data for Name: user_profiles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_profiles (id, user_id, display_name, bio, profile_picture, city, country, pincode, created_at, updated_at) FROM stdin;
\.


--
-- TOC entry 4892 (class 0 OID 16504)
-- Dependencies: 226
-- Data for Name: user_progress; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_progress (id, user_id, tip_challenge_id, status, started_at, completed_at, created_at, updated_at) FROM stdin;
\.


--
-- TOC entry 4882 (class 0 OID 16412)
-- Dependencies: 216
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, username, password, email, first_name, last_name, contact_number, active_status, role, created_at, updated_at) FROM stdin;
\.


--
-- TOC entry 4914 (class 0 OID 0)
-- Dependencies: 231
-- Name: comments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comments_id_seq', 1, false);


--
-- TOC entry 4915 (class 0 OID 0)
-- Dependencies: 229
-- Name: community_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.community_id_seq', 1, false);


--
-- TOC entry 4916 (class 0 OID 0)
-- Dependencies: 227
-- Name: global_changes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.global_changes_id_seq', 1, false);


--
-- TOC entry 4917 (class 0 OID 0)
-- Dependencies: 219
-- Name: organizations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.organizations_id_seq', 1, false);


--
-- TOC entry 4918 (class 0 OID 0)
-- Dependencies: 221
-- Name: sustainability_metrics_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sustainability_metrics_id_seq', 1, false);


--
-- TOC entry 4919 (class 0 OID 0)
-- Dependencies: 223
-- Name: tips_challenges_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tips_challenges_id_seq', 1, false);


--
-- TOC entry 4920 (class 0 OID 0)
-- Dependencies: 217
-- Name: user_profiles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_profiles_id_seq', 1, false);


--
-- TOC entry 4921 (class 0 OID 0)
-- Dependencies: 225
-- Name: user_progress_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_progress_id_seq', 1, false);


--
-- TOC entry 4922 (class 0 OID 0)
-- Dependencies: 215
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 1, false);


--
-- TOC entry 4729 (class 2606 OID 16560)
-- Name: comments comments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);


--
-- TOC entry 4727 (class 2606 OID 16544)
-- Name: community community_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.community
    ADD CONSTRAINT community_pkey PRIMARY KEY (id);


--
-- TOC entry 4725 (class 2606 OID 16533)
-- Name: global_changes global_changes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.global_changes
    ADD CONSTRAINT global_changes_pkey PRIMARY KEY (id);


--
-- TOC entry 4715 (class 2606 OID 16470)
-- Name: organizations organizations_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organizations
    ADD CONSTRAINT organizations_name_key UNIQUE (name);


--
-- TOC entry 4717 (class 2606 OID 16468)
-- Name: organizations organizations_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organizations
    ADD CONSTRAINT organizations_pkey PRIMARY KEY (id);


--
-- TOC entry 4719 (class 2606 OID 16481)
-- Name: sustainability_metrics sustainability_metrics_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sustainability_metrics
    ADD CONSTRAINT sustainability_metrics_pkey PRIMARY KEY (id);


--
-- TOC entry 4721 (class 2606 OID 16502)
-- Name: tips_challenges tips_challenges_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tips_challenges
    ADD CONSTRAINT tips_challenges_pkey PRIMARY KEY (id);


--
-- TOC entry 4713 (class 2606 OID 16436)
-- Name: user_profiles user_profiles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_profiles
    ADD CONSTRAINT user_profiles_pkey PRIMARY KEY (id);


--
-- TOC entry 4723 (class 2606 OID 16511)
-- Name: user_progress user_progress_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_progress
    ADD CONSTRAINT user_progress_pkey PRIMARY KEY (id);


--
-- TOC entry 4707 (class 2606 OID 16425)
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- TOC entry 4709 (class 2606 OID 16421)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 4711 (class 2606 OID 16423)
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- TOC entry 4736 (class 2606 OID 16561)
-- Name: comments comments_community_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_community_id_fkey FOREIGN KEY (community_id) REFERENCES public.community(id);


--
-- TOC entry 4737 (class 2606 OID 16566)
-- Name: comments comments_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 4735 (class 2606 OID 16545)
-- Name: community community_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.community
    ADD CONSTRAINT community_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 4731 (class 2606 OID 16487)
-- Name: sustainability_metrics sustainability_metrics_organization_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sustainability_metrics
    ADD CONSTRAINT sustainability_metrics_organization_id_fkey FOREIGN KEY (organization_id) REFERENCES public.organizations(id);


--
-- TOC entry 4732 (class 2606 OID 16482)
-- Name: sustainability_metrics sustainability_metrics_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sustainability_metrics
    ADD CONSTRAINT sustainability_metrics_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 4730 (class 2606 OID 16437)
-- Name: user_profiles user_profiles_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_profiles
    ADD CONSTRAINT user_profiles_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 4733 (class 2606 OID 16517)
-- Name: user_progress user_progress_tip_challenge_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_progress
    ADD CONSTRAINT user_progress_tip_challenge_id_fkey FOREIGN KEY (tip_challenge_id) REFERENCES public.tips_challenges(id);


--
-- TOC entry 4734 (class 2606 OID 16512)
-- Name: user_progress user_progress_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_progress
    ADD CONSTRAINT user_progress_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


-- Completed on 2024-07-22 19:32:08

--
-- PostgreSQL database dump complete
--

