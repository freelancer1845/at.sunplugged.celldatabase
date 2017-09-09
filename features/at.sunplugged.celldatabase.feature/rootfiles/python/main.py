'''
Created on 30.08.2017

@author: Jascha Riedel
'''

import sys
import evaluation
import os
import codecs, json
from evaluation.CellDataObject import CellDataObject

if __name__ == '__main__':
    args = sys.argv[1:]
    
    dataFileName = args[0]
    
    data = evaluation.fileIO.readLabViewFile(dataFileName)
    dataResult =  CellDataObject.createFromData(os.path.basename(dataFileName), data, 1.2, 890.0)
    
    dataResultDict = {
                    'ID': dataResult.Id,
                    'Voc': dataResult.Voc,
                    'Isc': dataResult.Isc,
                    'Jsc': dataResult.Jsc,
                    'Area': dataResult.Area,
                    'PowerInput': dataResult.powerInput,
                    'FF': dataResult.FF,
                    'Eff': dataResult.Eff,
                    'Rp': dataResult.Rp,
                    'Rs':dataResult.Rs,
                    'MaximumPower': dataResult.Mpp,
                    'MaximumPowerV': dataResult.MppU,
                    'MaximumPowerI': dataResult.MppI
                      }
    print(args[1] + "data.json")
    json.dump({'data': data.tolist()}, codecs.open(args[1] + "\\data.json", 'w', encoding='utf-8'), sort_keys=True, indent=4)
    json.dump(dataResultDict, codecs.open(args[1] + "\\result.json", 'w', encoding='utf-8'), sort_keys=True, indent=4)
    print(sys.argv[0])
    sys.exit(0)
    
