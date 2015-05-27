(ns mesh-clj.core)

(import 'megamu.mesh.Voronoi)

(defn- points->array [points]
  (let [l1 (count points)
        l2 (count (first points))
        a (make-array Float/TYPE l1 l2)]
    (doseq [i (range l1)
            j (range l2)]
      (aset a i j (get-in points [i j])))
    a))
 
(defn- array->point [a]
  (vec a))
 
(defn- array->points [a]
  (mapv array->point a))
 
(defn voronoi [points]
  (let [v (Voronoi. (points->array points))]
    (mapv (comp array->points #(.getCoords %)) (.getRegions v))))
