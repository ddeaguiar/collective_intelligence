def sqr(x)
  x*x
end

def sum_of_sqr(*data)
  data.inject(0) do |acc, x|
    acc += sqr(x)
  end
end

#page 10
def euclidean_distance(x1,y1,x2,y2)
  1/(1 + Math.sqrt(sum_of_sqr((x1 - x2), (y1 - y2))))
end

#page 11
def sim_distance(prefs, person1, person2)
  si = prefs[person1].keys & prefs[person2].keys
  
  return 0 if si.size == 0
  
  sum_of_square_diffs = si.inject(0) do |sum_of_square_diffs, key|
    sum_of_square_diffs += sqr(prefs[person1][key] - prefs[person2][key])
  end
  1/(1 + Math.sqrt(sum_of_square_diffs))
end