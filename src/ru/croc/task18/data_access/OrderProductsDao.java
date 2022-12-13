package ru.croc.task18.data_access;

import ru.croc.task17.core.entities.Order;
import ru.croc.task17.core.entities.Product;
import ru.croc.task18.exceptions.ShopException;
import java.util.List;

public interface OrderProductsDao {

	/**
	 * Поиск в базе данных товара с указанным артикулом
	 * @param productCode артикул товара
	 * @return товар или null, если соответствующий товар не найден
	 */
	Product findProduct(String productCode);

	/**
	 * Создание нового товара
	 * @param product товар
	 * @return товар, добавленный в базу данных
	 * @throws ShopException если товар с таким артикулом уже существует
	 */
	Product createProduct(Product product) throws ShopException;


	/**
	 * Изменение информации о товаре
	 * @param product товар с новыми значениями полей
	 * @return обновленный товар из базы данных
	 * @throws ShopException если товар не найден
	 */
	Product updateProduct(Product product) throws ShopException;

	/**
	 * Удаление товара и всех упоминаний о нем в заказах
	 * @param productCode артикул товара для удаления
	 * @throws ShopException если товар не найден
	 */
	void deleteProduct(String productCode) throws ShopException;

	/**
	 * Создание заказа
	 * @param userLogin логин заказчика
	 * @param products список товаров
	 * @return заказ, добавленный в базу данных
	 * @throws ShopException если один или несколько продуктов не найдены
	 */
	Order createOrder(String userLogin, List<Product> products) throws ShopException;
}
