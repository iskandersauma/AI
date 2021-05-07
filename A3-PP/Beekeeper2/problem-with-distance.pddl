;; Problem definition
(define (problem problem-1)

  ;; Specifying the domain for the problem
  (:domain honey-domain)

  ;; Objects definition
  (:objects
    ; Positions
    home
    forest-1 forest-2
    garden
    ; Arms
    left right
    ; Bee-houses
    bee-house-1 bee-house-2
    ; Honey pots
    honey-pot-1 honey-pot-2
    ; Costumes
    costume
  )

  ;; Initial state of problem 1
  (:init
    ;; Declaration of the objects
    (POSITION home)
    (POSITION garden)
    (POSITION forest-1)
    (POSITION forest-2)
    (ARM left) 
    (ARM right)
    (OBJECT bee-house-1) 
    (OBJECT bee-house-2)
    (BEE-HOUSE bee-house-1) 
    (BEE-HOUSE bee-house-2)
    (OBJECT honey-pot-1) 
    (OBJECT honey-pot-2)
    (HONEY-POT honey-pot-1) 
    (HONEY-POT honey-pot-2)
    (OBJECT costume)
    (COSTUME costume)

    ;; Declaration of the predicates of the objects
    (free left) (free right)
    (empty honey-pot-1) 
    (empty honey-pot-2)
    (has-honey bee-house-1) 
    (has-honey bee-house-2)
    (person-at home)
    (object-at bee-house-1 forest-1) 
    (object-at bee-house-2 forest-2)
    (object-at costume garden)
    (object-at honey-pot-1 home) 
    (object-at honey-pot-2 home)

    ;; Declaration of the numeric fluents
    (= (distance home garden) 3)
    (= (distance garden home) 3)
    (= (distance home forest-1) 18)
    (= (distance forest-1 home) 18)
    (= (distance forest-1 forest-2) 5)
    (= (distance forest-2 forest-1) 5)
    (= (distance forest-2 home) 22)
    (= (distance home forest-2) 22)
    (= (distance garden forest-1) -1)
    (= (distance garden forest-2) -1)
    (= (distance forest-1 garden) -1)
    (= (distance forest-2 garden) -1)
    (= (amount-of-steps) 0)

  )

  ;; Goal specification
  (:goal (and
    (object-at honey-pot-1 home)
    (object-at honey-pot-2 home)
    (object-at costume garden)
    (not (empty honey-pot-1))
    (not (empty honey-pot-2))
    (person-at home))
  )

  ;; Metric to define how good is a plan
  (:metric minimize (amount-of-steps))

)
