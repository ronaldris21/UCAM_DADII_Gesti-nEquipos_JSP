package edu.ucam.dao;

import java.util.ArrayList;

public interface DAO<T> {
	public ArrayList<T> getAll();
	public T getById(int id);
	public boolean delete(int id); ///TODO:  No poder borrar el admin
	public boolean update(int id, T objNuevo);
	public boolean insert(T objNuevo);
}
