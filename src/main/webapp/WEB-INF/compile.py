#!/usr/bin/python
#Compiles javascripts using closure library
import getopt, sys, os

closure_compiler = ''
closure_base = ''
js_sources = ''
main_js = ''

def main(argv):
  try:
    opts, args = getopt.getopt(argv, "c:b:j:m:", ["closure-compiler=", "closure-base=", "js-sources=", "main_js="])
  except getopt.GetoptError:
    #print str(err) # Will print something like "option -x not recognized"
    print("Some thing went wrong.")
    sys.exit()
  
  global closure_compiler, closure_base, js_sources, main_js
  for o, a in opts:
    if o in ("-c", "--closure-compiler"):
      closure_compiler = a
    elif o in ("-b", "--closure-base"):
      closure_base = a
    elif o in ("-j", "--js-sources="):
      js_sources = a
    elif o in ("-m", "--main-js="):
      main_js = a
    else:
      assert False, "Unhandled option: " + o
      
  if closure_compiler == '':
    print("Closure compiler path not set.")
    sys.exit()
  if closure_base == '':
    print("Closure base path not set.")
    sys.exit()
  if js_sources == '':
    print("Js sources base not set.")
    sys.exit()
  if main_js == '':
    print("Main js file not set.")
    sys.exit()
    
  # Build the command line
  command = closure_compiler + \
      " -p " + closure_base + " -p " + js_sources + \
      " -i " + main_js + " -o script"

  #print("Executing: ", command)
  os.system(command)

if __name__ == '__main__':
  main(sys.argv[1:])