;; Domain definition
(define (domain honey-domain)

  ;; Predicates: Properties of objects that we are interested in (boolean)
  (:predicates
    (POSITION ?x) ; True if x is a position
    (ARM ?x) ; True if x is an arm
    (OBJECT ?x) ; True if x is an object
    (BEE-HOUSE ?x) ; True if x is a bee-house
    (HONEY-POT ?x) ; True if x is a honey pot
    (COSTUME ?x) ; True if x is a costume
    (person-at ?x) ; True if the person is at x
    (object-at ?x ?y) ; True if x is an object (bee-house, honey-pot or costume), y is a position and x is in y
    (free ?x) ; True if x is an arm and there is NO honey pot in it
    (hold ?x ?y) ; True if x is an arm, y is a honey pot and x holds y
    (wearing ?x) ; True if x is a costume and the person is wearing it
    (empty ?x) ; True if x is a honey pot and it is empty
    (has-honey ?x) ; True if the bee-house x has honey
  )

  (:functions
    (distance ?pos1 ?pos2)
    (amount-of-steps))

  (:action measure-distance
    ; -x: initial position
    ; -y: next step position
    :Parameters (?pos1 ?pos2)
    :precondition (and (>= (distance ?pos1 ?pos2) 0)
			(POSITION ?pos1)
			(POSITION ?pos2)
			(person-at ?pos1)
			(not (person-at ?pos2)))
    :effect (and (increase (amount-of-steps) (distance ?pos1 ?pos2))
		(person-at ?pos2)
		(not (person-at ?pos1)))
 )

  ; The person can take a costume x and put it on if she and the costume are both at position y
  ; Parameters:
  ; - x: the costume
  ; - y: the position where the person will put on the costume
  (:action wear-at
    :parameters (?x ?y)
    :precondition (and (POSITION ?y)
			(COSTUME ?x) 
			(person-at ?y)
			(not(wearing ?x))
			(object-at ?x ?y))
    :effect (and (wearing ?x)
		(not (object-at ?x ?y))
		(person-at ?y))
  )

  ; The person can take off the costume x at position y
  ; Parameters:
  ; - x: the costume
  ; - y: the position where the costume will be taken off
  (:action take-off-at
    :parameters (?x ?y)
    :precondition (and (POSITION ?y)
			(COSTUME ?x) 
			(person-at ?y)
			(wearing ?x))
    :effect (and (object-at ?x ?y)
		(not(wearing ?x))
		(person-at ?y))
  )

  ; The person can collect all honey from bee house w to honey pot x if 
  ; there is honey in the bee house, the honey pot is empty, 
  ; the person is wearing costume y, has a free arm v, and 
  ; she, the bee house and the honey pot are in the same position z.
  ; - x: the honey pot
  ; - y: the costume
  ; - z: the position where the person collects the honey
  ; - w: the bee house
  ; - v: the arm
  (:action collect
    :parameters (?x ?y ?z ?w ?v)
    :precondition (and (POSITION ?z)
			(person-at ?z)
			(ARM ?v)
			(not (free ?v))
			(COSTUME ?y)
			(wearing ?y)
			(HONEY-POT ?x)			
			(has-honey ?w)
			(BEE-HOUSE ?w)
			(empty ?x)
			(object-at ?w ?z))
     :effect (and (not (free ?v))
		(not(empty ?x))
		(person-at ?z)
		(not (has-honey ?w)))
  )

  ; The person can pick-up honey pot x with free arm y at position z
  ; - x: the honey pot
  ; - y: the arm
  ; - z: the position where the object is picked up
  (:action pick-up
    :parameters(?x ?y ?z)
    :precondition (and (POSITION ?z)
			(person-at ?z)
			(ARM ?y)
			(free ?y)
			(object-at ?x ?z)
			(HONEY-POT ?x)
			(not(hold ?y ?x)))
    :effect (and (hold ?y ?x)
		(not(free ?y))
		(not (object-at ?x ?z)))
  )

  ; The person can drop off honey pot x that she holds in arm y at position z
  ; - x: the honey pot
  ; - y: the arm
  ; - z: the position where the object is dropped off
  (:action drop-off
    :parameters (?x ?y ?z)
    :precondition (and (POSITION ?z)
			(person-at ?z)
			(ARM ?y)
			(not(free ?y))
			(HONEY-POT ?x)
			(hold ?y ?x))
    :effect (and (not(hold ?y ?x))
		(free ?y)
		(object-at ?x ?z))
  )

)
