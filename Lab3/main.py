import re
import time
import zipfile

start = time.clock()
result = 0
quantity = 0
pattern = r'.*01/Jul/1995.*(((:00:)(3[5-9]|[4-5][0-9])(:))|((:01:)(0[0-9]|1[1-2])(:))).*GET.*200 '
z = zipfile.ZipFile('access_log_Jul95.zip')
file = z.open('access_log_Jul95')
for line in file:
    if re.search(pattern, str(line)):
        r = re.sub(pattern, '', str(line))
        result += int(r[:-3])

print(result)
z.close()
elapsed = time.clock()
elapsed = elapsed - start
print( "Time spent in (function name) is: ", elapsed)
