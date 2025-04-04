GRADLE := ./gradlew

current: c

f: fmt
fmt:
	$(GRADLE) spotlessApply

t: test
test:
	$(GRADLE) test

c: check
check:
	$(GRADLE) check

r: run
run:
	$(GRADLE) run
