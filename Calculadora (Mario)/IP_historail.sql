PGDMP         *                x            ip    12.2    12.2                 0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    49173    ip    DATABASE     �   CREATE DATABASE ip WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Spain.1252' LC_CTYPE = 'Spanish_Spain.1252';
    DROP DATABASE ip;
                postgres    false            �            1259    49179    ip    TABLE     �  CREATE TABLE public.ip (
    ip character varying(20) NOT NULL,
    mask character varying(20) NOT NULL,
    red character varying(20),
    estado character varying(20),
    apipa character varying(20),
    reserva character varying(20),
    clase character varying(20),
    broadcast character varying(20),
    gateway character varying(20),
    rango character varying(36),
    multicast character varying(20)
);
    DROP TABLE public.ip;
       public         heap    postgres    false            �
          0    49179    ip 
   TABLE DATA           p   COPY public.ip (ip, mask, red, estado, apipa, reserva, clase, broadcast, gateway, rango, multicast) FROM stdin;
    public          postgres    false    202   z       ~
           2606    49183 
   ip ip_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.ip
    ADD CONSTRAINT ip_pkey PRIMARY KEY (ip, mask);
 4   ALTER TABLE ONLY public.ip DROP CONSTRAINT ip_pkey;
       public            postgres    false    202    202            �
   _  x����n� @������z�nzk�^z��*�n�qm��h��n�`�s`�a����EI�r�.%�>��5�����g����~�~P_�Q��L�k�5L�M�`7K��b��֭Sqz9����ȁ�4r��u*[q
��9ωմZ�h/��n�;4} �4V����+J���<��m��R�9e��,0mR0������ �e�2 ��w���T�L��MFD�H�U�B�ؓ�1�QB�o�)��
L�c��5^��>w�nPm��NY��J��em��lw�=����G��>���#���8z{:��n����;wX����c[������n"�Z��L`���ֽ˒w�$�c8     