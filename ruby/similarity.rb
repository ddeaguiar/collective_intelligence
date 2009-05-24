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

#page 13
def sim_pearson(prefs, person1, person2)
  si = prefs[person1].keys & prefs[person2].keys
  n = si.size
  return 0 if n == 0
  
  sum1 = 0
  sum2 = 0
  sum1sq = 0
  sum2sq = 0
  psum = 0
  si.each do |key|
    sum1 += prefs[person1][key]
    sum2 += prefs[person2][key]
    sum1sq += sqr(prefs[person1][key])
    sum2sq += sqr(prefs[person2][key])
    psum += prefs[person1][key] * prefs[person2][key]
  end
  
  num = psum - (sum1 * sum2 / n)
  #Corrects the typo in the First edition printing which forgets
  #the sqrt.
  den = Math.sqrt((sum1sq - sqr(sum1) / n) * (sum2sq - sqr(sum2) / n))
  
  den == 0 ? 0 : num/den
end

#page 14
def top_matches(prefs, person, n=5, similarity=:sim_pearson)
  scores = (prefs.keys - [person]).collect do |other|
              [send(similarity, prefs, person, other), other]
            end
  scores.sort().reverse().slice(0..n-1)
end