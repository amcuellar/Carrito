CREATE TABLE ARTICULOS
(
REFERENCIA VARCHAR2(20),
CATEGORIA VARCHAR2(20),
MARCA VARCHAR2(20),
TIPO VARCHAR2(20),
CILINDRAJE NUMBER,
PRECIO NUMBER
);

CREATE TABLE INVENTARIO
(
CANTIDAD NUMBER,
REFERENCIA VARCHAR2(20)
);

CREATE SEQUENCE SQ_REFERENCIA
START WITH 1
INCREMENT BY 1;

create or replace PACKAGE PKG_ARTICULOS AS
  PROCEDURE CREAR_ARTICULO(CATEGORIA VARCHAR2, NOMBRE VARCHAR2, MARCA VARCHAR2, TIPO VARCHAR2, CILINDRAJE NUMBER, PRECIO NUMBER);
  PROCEDURE BORRAR_ARTICULO(P_REF VARCHAR2);
  PROCEDURE MODIFICAR_ARTICULO(P_REF VARCHAR2, P_CAT VARCHAR2, P_NOM VARCHAR2, P_MARCA VARCHAR2, P_TIPO VARCHAR2, P_CIL NUMBER, P_PRECIO NUMBER);
END PKG_ARTICULOS;

create or replace PACKAGE BODY PKG_ARTICULOS AS
  PROCEDURE CREAR_ARTICULO(CATEGORIA VARCHAR2, NOMBRE VARCHAR2, MARCA VARCHAR2, TIPO VARCHAR2, CILINDRAJE NUMBER, PRECIO NUMBER) IS
  V_REFERENCIA VARCHAR2(20);
  BEGIN
    SELECT REPLACE(TO_CHAR(SYSDATE), '/')||LPAD(SQ_REFERENCIA.NEXTVAL, 4, '0') INTO V_REFERENCIA FROM DUAL;
    INSERT INTO ARTICULOS VALUES(V_REFERENCIA, CATEGORIA, NOMBRE, MARCA, TIPO, CILINDRAJE, PRECIO);
    COMMIT;
  END CREAR_ARTICULO;
  
  PROCEDURE BORRAR_ARTICULO(P_REF VARCHAR2) IS
  BEGIN
    DELETE FROM ARTICULOS WHERE REFERENCIA = P_REF;
    COMMIT;
  END BORRAR_ARTICULO;

  PROCEDURE MODIFICAR_ARTICULO(P_REF VARCHAR2, P_CAT VARCHAR2, P_NOM VARCHAR2, P_MARCA VARCHAR2, P_TIPO VARCHAR2, P_CIL NUMBER, P_PRECIO NUMBER) IS
  BEGIN
    UPDATE ARTICULOS 
    SET CATEGORIA = P_CAT, MARCA = P_MARCA, NOMBRE = P_NOM, TIPO = P_TIPO, CILINDRAJE = P_CIL, PRECIO = P_PRECIO
    WHERE REFERENCIA = P_REF;
    COMMIT;
  END MODIFICAR_ARTICULO;
END PKG_ARTICULOS;