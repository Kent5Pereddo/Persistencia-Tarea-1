package com.foodpacking.foodpackingorm;

import com.foodpacking.modelo.Empaque;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class FoodPackingORM {
    public static void main(String[] args) {
        // Inicializar la conexión usando la unidad de persistencia del ORM
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FoodPackingPU");
        EntityManager em = emf.createEntityManager();

        try {
            // ==========================================
            // 1. CREAR (Insertar un nuevo registro)
            // ==========================================
            em.getTransaction().begin();
            Empaque nuevoEmpaque = new Empaque("Caja Térmica para Alimentos", "Cartón Corrugado Ecológico", 500);
            em.persist(nuevoEmpaque); // El ORM convierte el objeto en sentencia SQL INSERT
            em.getTransaction().commit();
            System.out.println("--> CREADO EXITOSAMENTE: " + nuevoEmpaque);

            // ==========================================
            // 2. LEER (Consultar registros)
            // ==========================================
            Long idBuscado = nuevoEmpaque.getId();
            Empaque empaqueLeido = em.find(Empaque.class, idBuscado); // El ORM realiza el SELECT automáticamente
            System.out.println("--> LEÍDO DE LA BD: " + empaqueLeido);

            // ==========================================
            // 3. ACTUALIZAR (Modificar un registro existente)
            // ==========================================
            em.getTransaction().begin();
            empaqueLeido.setStock(450); // Modificamos el stock en memoria
            em.merge(empaqueLeido);     // El ORM sincroniza con un UPDATE en la BD
            em.getTransaction().commit();
            System.out.println("--> ACTUALIZADO: " + em.find(Empaque.class, idBuscado));

            // ==========================================
            // 4. BORRAR (Eliminar un registro)
            // ==========================================
            em.getTransaction().begin();
            Empaque empaqueAEliminar = em.find(Empaque.class, idBuscado);
            if (empaqueAEliminar != null) {
                em.remove(empaqueAEliminar); // El ORM ejecuta el DELETE en la BD
                System.out.println("--> BORRADO EXITOSAMENTE.");
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}