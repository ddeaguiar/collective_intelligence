def sqr(x)
  x*x
end

def sum_of_sqr(*data)
  data.inject(0) do |acc, x|
    acc += sqr(x)
  end
end

def euclidean_distance(x1,y1,x2,y2)
  1/(1 + Math.sqrt(sum_of_sqr((x1 - x2), (y1 - y2))))
end

def sim_distance(prefs, person1, person2)
  # si = {}
  # prefs[person1].each do |item|
  #   key, value = item
  #   if prefs[person2].has_key(key)
  # end
end