describe("hello",function(){
	it("should work",function(){
		expect(true).toBe(true);
	})
})

describe('filter',function(){
	beforeEach(module('myApp'));

	describe('reverse',function(){
		it('should reverse a string',inject(function (reverseFilter){
			expect(reverseFilter('ABCD')).toEqual('DCBA');
			expect(reverseFilter('John')).toEqual('nhoJ');
		}))
	})
})
